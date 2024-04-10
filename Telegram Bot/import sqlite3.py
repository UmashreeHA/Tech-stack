import sqlite3

# Connect to the database
conn = sqlite3.connect('views_tracker.db')
cursor = conn.cursor()

# Execute a query
cursor.execute("SELECT * FROM your_table")
rows = cursor.fetchall()

# Do something with the rows
for row in rows:
    print(row)

# Close the connection
conn.close()
