import pandas as pd
import requests
import numpy as np

# Define the URL of your Spring backend endpoint
url = "http://localhost:8080/books/CreateMultipleBooks"

# Load the Excel file
file_path = 'PathtoFile.xlsx'
try:
    df = pd.read_excel(file_path, engine='openpyxl')
except FileNotFoundError:
    print(f"File not found: {file_path}")
    exit(1)
except Exception as e:
    print(f"Error reading the Excel file: {e}")
    exit(1)

# Clean the DataFrame
df = df.replace([np.inf, -np.inf], np.nan).fillna(0)

# Convert the DataFrame to a list of dictionaries
books = df.to_dict('records')

# Function to send books to the backend
def send_books(books_batch):
    try:
        response = requests.post(url, json=books_batch)
        if response.status_code == 200:
            print("Batch uploaded successfully")
        else:
            print(f"Failed to upload batch: {response.status_code}")
    except requests.exceptions.RequestException as e:
        print(f"Error sending request: {e}")

# Process the books in batches of 50
batch_size = 50
for i in range(0, len(books), batch_size):
    batch = books[i:i + batch_size]
    send_books(batch)
    
    # Ask the user if they want to continue after each batch
    if i + batch_size < len(books):
        user_input = input("Do you want to continue with the next batch? (yes/no): ")
        if user_input.lower() != 'yes':
            break

print("All batches processed.")