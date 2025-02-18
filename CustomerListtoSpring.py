import pandas as pd
import requests
import numpy as np

# Define the URL of your Spring backend endpoint for customers
url = "http://localhost:8080/customers/CreateMultipleCustomers"

# Load the Excel file
file_path = 'PathtoFile.xlsx'  # Update this to the correct path of your Excel file
try:
    df = pd.read_excel(file_path, engine='openpyxl')
except FileNotFoundError:
    print(f"File not found: {file_path}")
    exit(1)
except Exception as e:
    print(f"Error reading the Excel file: {e}")
    exit(1)

# Clean the DataFrame
df = df.replace([np.inf, -np.inf], np.nan).fillna('')

# Convert the DataFrame to a list of dictionaries
customers = df.to_dict('records')

# Function to send customers to the backend
def send_customers(customers_batch):
    try:
        response = requests.post(url, json=customers_batch)
        if response.status_code == 200:
            print("Batch uploaded successfully")
        else:
            print(f"Failed to upload batch: {response.status_code}")
    except requests.exceptions.RequestException as e:
        print(f"Error sending request: {e}")

# Process the customers in batches of 50
batch_size = 100
for i in range(0, len(customers), batch_size):
    batch = customers[i:i + batch_size]
    send_customers(batch)
    
    # Ask the user if they want to continue after each batch
    if i + batch_size < len(customers):
        user_input = input("Do you want to continue with the next batch? (yes/no): ")
        if user_input.lower() != 'yes':
            break

print("All batches processed.")