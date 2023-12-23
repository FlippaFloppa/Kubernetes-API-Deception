import csv
import random

class ProductGenerator:

    def __init__(self, wordlists_path, debug=0):
        self.wordlists_path = wordlists_path
        self.debug = debug

    def generate_product(self):

        #PRODUCT NAME
        with open(self.wordlists_path + '/products.csv', 'r') as f:
            reader = csv.reader(f)
            products= list(reader)

        rand_product_name = random.choice(products)[0]

        #PRODUCT ID
        rand_product_id = rand_product_name.lower()[:3] + str(random.randint(0,100))

        #PRICE
        rand_price = random.randint(0,100) + random.randint(0,99)/100

        rand_desc =  "descrizione prodotto " + rand_product_name + " " + rand_product_id + " " + str(rand_price)

        # OUTPUT
        if (self.debug == 1):
            print(rand_product_name)
            print(rand_product_id)
            print(rand_price)
            print("------------------")

        return {
            "productName": rand_product_name,
            "productId": rand_product_id,
            "description": rand_desc,
            "price": rand_price
        }
