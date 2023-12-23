from scripts.user_generator import UserGenerator
from scripts.product_generator import ProductGenerator
import random
import requests
import time
import os
import json
from dotenv import load_dotenv
load_dotenv()

wordlist_path = os.getenv('WORDLIST_PATH', 'wordlists')
api_url = os.getenv('API_SERVER_URL')
auth_url = os.getenv('AUTH_SERVER_URL')
client_id = os.getenv('CLIENT_ID')
client_secret = os.getenv('CLIENT_SECRET')
username = os.getenv('USERNAME')
password = os.getenv('PASSWORD')

# Get bearer token
print("Getting bearer token...")
header = {'Content-Type': 'application/x-www-form-urlencoded'}
payload = {'grant_type': 'password', 'username': username, 'password': password, 'client_id': client_id, 'client_secret': client_secret}
r = requests.post(auth_url, data=payload, headers=header)
bearer_token = r.json()['access_token']
print("Bearer token: " + bearer_token)

# User creatiom
print("Creating users...")
user_generator = UserGenerator(wordlist_path, debug=0)
loop_times = random.randint(25, 60)
users = []
for i in range(0, loop_times):
    users.append(user_generator.generate_user())

r = requests.post(api_url + '/users/addAll', data=json.dumps(users), headers={"Authorization": "Bearer " + bearer_token, "Content-Type": "application/json", "Accept": "application/json"})
if r.status_code != 200 and r.status_code != 201:
    print("Error creating users! " + str(r.status_code))
    print(r.text)
    exit(1)
print("Users created!")

# Product creation
print("Creating products...")
product_generator = ProductGenerator(wordlist_path, debug=0)
loop_times = random.randint(60, 80)
products = []
for i in range(0, loop_times):
    products.append(product_generator.generate_product())

r = requests.post(api_url + '/products/addAll', data=json.dumps(products), headers={"Authorization": "Bearer " + bearer_token, "Content-Type": "application/json", "Accept": "application/json"})
if r.status_code != 200 and r.status_code != 201:
    print("Error creating products! " + str(r.status_code))
    print(r.text)
    exit(1)
print("Products created!")

print("Done!")

