import csv
import random
import string

class OrderGenerator:

    def __init__(self, wordlists_path, debug=0):
        self.wordlists_path = wordlists_path
        self.debug = debug

    def generate_order(self):

        #PRODUCTS
        with open(self.wordlists_path + 'products.csv', 'r') as f:
            reader = csv.reader(f)
            products= list(reader)
        
        #print(products)
        rand_product_names = []
        rand_quantities = []
        for i in range(random.randint(1,10)):
            rand_product_name = random.choice(products)[0]
            rand_quantity = random.randint(1,10)
            rand_product_names.append(rand_product_name)
            rand_quantities.append(rand_quantity)
            
        #print(rand_product_names)
        
        #ORDER ID
        rand_order_id = random.choice(string.ascii_lowercase) + random.choice(string.ascii_lowercase) + str(random.randint(1,9)) + str(random.randint(0,9)) + str(random.randint(0,9))
        #print(rand_order_id)

        # OUTPUT
        if (self.debug == 1):
            print(rand_product_names)
            print(rand_quantities)
            print(rand_order_id)
            print("------------------")

        return {
            "product names": rand_product_names,
            "quantities ": rand_quantities,
            "order id": rand_order_id
        }

def main():
    pg = OrderGenerator("python-data-generator/wordlists/", debug=1)
    pg.generate_order()


if __name__ == "__main__":
    main()

