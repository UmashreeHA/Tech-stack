from telegram import Updater, CommandHandler, CallbackContext
import requests
import sqlite3
from datetime import datetime, date, timedelta
import time


TOKEN = 'UmaHAbot'


GIPHY_API_KEY = 'sA3bGlaPGuJhQwEBBFdrpa8zGZqnrQxy'


DB_FILE = 'views_tracker.db'


updater = Updater(token=TOKEN, use_context=True)
dispatcher = updater.dispatcher

# Function to handle /start command
def start(update, context):
    context.bot.send_message(chat_id=update.effective_chat.id, text="Welcome to the Daily Giphy Views Tracker Bot! Use /help to see available commands.")

# Function to handle /help command
def help_command(update, context):
    context.bot.send_message(chat_id=update.effective_chat.id, text="Available commands:\n"
                                                                    "/start - Start the bot\n"
                                                                    "/help - Show this help message\n"
                                                                    "/track <giphy_id> - Start tracking views for the specified Giphy gif\n"
                                                                    "/stats <giphy_id> - Show stats for the specified Giphy gif")

# Function to track views for a specific Giphy gif
def track_giphy(update, context):
    if len(context.args) == 0:
        context.bot.send_message(chat_id=update.effective_chat.id, text="Please provide a Giphy ID to track.")
        return

    giphy_id = context.args[0]  # Get Giphy ID from command arguments

    # Make request to Giphy API to get gif details
    response = requests.get(f"https://api.giphy.com/v1/gifs/{giphy_id}?api_key={GIPHY_API_KEY}")
    if response.status_code == 200:
        gif_data = response.json().get('data')
        if gif_data:
            # Extract view count
            view_count = gif_data.get('views', 0)
            # Store view count in database
            with sqlite3.connect(DB_FILE) as conn:
                cursor = conn.cursor()
                cursor.execute("INSERT INTO views (giphy_id, view_count, date) VALUES (?, ?, ?)",
                               (giphy_id, view_count, date.today()))
                conn.commit()
            context.bot.send_message(chat_id=update.effective_chat.id, text=f"Successfully started tracking views for Giphy gif {giphy_id}.")
        else:
            context.bot.send_message(chat_id=update.effective_chat.id, text="Failed to track views for the specified Giphy gif. Giphy ID not found.")
    else:
        context.bot.send_message(chat_id=update.effective_chat.id, text="Failed to track views for the specified Giphy gif.")

# Function to show stats for a specific Giphy gif
def show_stats(update, context):
    if len(context.args) == 0:
        context.bot.send_message(chat_id=update.effective_chat.id, text="Please provide a Giphy ID to show stats.")
        return

    giphy_id = context.args[0]  # Get Giphy ID from command arguments

    # Query database for view count
    with sqlite3.connect(DB_FILE) as conn:
        cursor = conn.cursor()
        cursor.execute("SELECT SUM(view_count) FROM views WHERE giphy_id = ?", (giphy_id,))
        total_views = cursor.fetchone()[0]
        if total_views is not None:
            context.bot.send_message(chat_id=update.effective_chat.id, text=f"Total views for Giphy gif {giphy_id}: {total_views}")
        else:
            context.bot.send_message(chat_id=update.effective_chat.id, text=f"No views found for the specified Giphy gif.")

# Function to send daily updates of project views
def send_daily_update(context: CallbackContext):
    # Query the database for all tracked Giphy IDs
    with sqlite3.connect(DB_FILE) as conn:
        cursor = conn.cursor()
        cursor.execute("SELECT DISTINCT giphy_id FROM views")
        giphy_ids = cursor.fetchall()

        # Iterate over each tracked Giphy ID
        for giphy_id_tuple in giphy_ids:
            giphy_id = giphy_id_tuple[0]
            # Query for the total views of the Giphy ID
            cursor.execute("SELECT SUM(view_count) FROM views WHERE giphy_id = ?", (giphy_id,))
            total_views = cursor.fetchone()[0]
            if total_views is not None:
                context.bot.send_message(chat_id=context.job.context, text=f"Daily update: Total views for Giphy gif {giphy_id}: {total_views}")
            else:
                context.bot.send_message(chat_id=context.job.context, text=f"Daily update: No views found for Giphy gif {giphy_id}.")

# Add handlers for commands
start_handler = CommandHandler('start', start)
dispatcher.add_handler(start_handler)

help_handler = CommandHandler('help', help_command)
dispatcher.add_handler(help_handler)

track_handler = CommandHandler('track', track_giphy, pass_args=True)
dispatcher.add_handler(track_handler)

stats_handler = CommandHandler('stats', show_stats, pass_args=True)
dispatcher.add_handler(stats_handler)

# Schedule daily updates
updater.job_queue.run_daily(send_daily_update, time=datetime.time(hour=12, minute=0, second=0), context=updater.bot.username)

# Start the bot
updater.start_polling()
updater.idle()