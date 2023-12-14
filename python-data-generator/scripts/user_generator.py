import randomtimestamp as rt
import csv
import random


class UserGenerator:

    def __init__(self, wordlists_path, debug=0):
        self.wordlists_path = wordlists_path
        self.debug = debug

    def generate_user(self):

        # TIMESTAMP
        rand_timestamp_raw = rt.randomtimestamp(
            start_year=2014, end_year=2023, text=True, pattern="%Y-%m-%d %H:%M:%S")
        rand_timestamp = rand_timestamp_raw.split(
            " ")[0] + "T" + rand_timestamp_raw.split(" ")[1]

        # FULL NAME
        with open(self.wordlists_path + '/fullnames.csv', 'r') as f:
            reader = csv.reader(f)
            fullnames = list(reader)

        rand_fullname = random.choice(fullnames)
        rand_firstname = rand_fullname[1]
        rand_lastname = rand_fullname[2]

        # USERNAME
        username = (rand_firstname[0] + '.' + rand_lastname).lower()

        # EMAIL ADDRESS
        with open(self.wordlists_path + '/maildomains.csv', 'r') as f:
            reader = csv.reader(f)
            mail_domains = list(reader)
            mail_domains = mail_domains[0]

        rand_maildomain = random.choice(mail_domains)

        separator = random.choice(["", ".", "_"])
        collapse = random.choice([0, 1])
        numsuffix = random.choice([0, 1])
        rand_numsuffix = str(random.randint(1, 10))
        if (collapse == 1):
            rand_email_raw = list(rand_firstname.lower())[
                0] + rand_lastname.lower()
        else:
            rand_email_raw = rand_firstname.lower() + separator + rand_lastname.lower()

        if (numsuffix == 1):
            rand_email_raw = rand_email_raw + rand_numsuffix

        rand_email = rand_email_raw + "@" + rand_maildomain

        # PASSWORD
        with open(self.wordlists_path + '/passwords.csv', 'r') as f:
            reader = csv.reader(f)
            passwords = list(reader)
            passwords = passwords[0]

        rand_password = random.choice(passwords)

        # OUTPUT
        if (self.debug == 1):
            print(rand_firstname)
            print(rand_lastname)
            print(username)
            print(rand_password)
            print(rand_email)
            print(rand_timestamp)
            print("------------------")

        return {
            "name": rand_firstname,
            "surname": rand_lastname,
            "username": username,
            "email": rand_email,
            "password": rand_password,
            "registrationDate": rand_timestamp
        }
