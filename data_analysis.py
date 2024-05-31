import pandas as pd

# Load the gapminder dataset
df = pd.read_csv('https://raw.githubusercontent.com/plotly/datasets/master/gapminderDataFiveYear.csv')

# Preview the dataset
print(df.head())

# Basic analysis
summary = df.describe()
print(summary)

import plotly.express as px

# Scatter plot of GDP per capita vs Life Expectancy
scatter_fig = px.scatter(df, x='gdpPercap', y='lifeExp', color='continent',hover_name='country', log_x=True, size_max=60,title='GDP per Capita vs Life Expectancy')

# Line chart of Life Expectancy over time for each continent
line_fig = px.line(df, x='year', y='lifeExp', color='continent',title='Life Expectancy Over Time by Continent')

import dash
from dash import dcc, html
from dash.dependencies import Input, Output

# Initialize the Dash app
app = dash.Dash(__name__)

# Define the layout of the dashboard
app.layout = html.Div([
    html.H1("Data Analysis Dashboard"),
    
    dcc.Graph(id='scatter-plot', figure=scatter_fig),
    
    dcc.Graph(id='line-chart', figure=line_fig),
    
    html.Label('Select Year:'),
    dcc.Slider(
        id='year-slider',
        min=df['year'].min(),
        max=df['year'].max(),
        value=df['year'].min(),
        marks={str(year): str(year) for year in df['year'].unique()},
        step=None
    ),
    
    dcc.Graph(id='bar-chart')
])

# Callback to update bar chart based on selected year
@app.callback(
    Output('bar-chart', 'figure'),
    [Input('year-slider', 'value')]
)
def update_bar_chart(selected_year):
    filtered_df = df[df['year'] == selected_year]
    bar_fig = px.bar(filtered_df, x='continent', y='pop', color='continent', title=f'Population by Continent in {selected_year}')
    return bar_fig

# Run the app
if __name__ == '__main__':
    app.run_server(debug=True)