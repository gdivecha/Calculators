# #This program creates random passwords based on the requirements provided by the user
# #You want to create random passwords based on the database to make passwords that make sense
#
# #Criteria:
# # the use of both upper-case and lower-case letters (case sensitivity)
# # inclusion of one or more numerical digits
# # inclusion of special characters, such as @, #, $
# # prohibition of words found in a password blocklist
# # prohibition of words found in the user's personal information
# # prohibition of use of company name or an abbreviation
# # prohibition of passwords that match the format of calendar dates, license plate numbers, telephone numbers, or other common numbers
#
# #No recurring numbers
# #No continuous numbers
# #Given range for the number of characters
#
# #Weak Passwords:
# # A dictionary word with some letters simply replaced by numbers (e.g., a1rplan3 or aer0plan0)
# # A repeated character or a series of characters(e.g., AAAAA or 12345)
# # A keyboard series of characters (e.g., qwerty or poiuy)
# #ever use < or > in your password, as both can cause problems in web browsers
#

import random

lowerCaseLetters = '''abcdefghijklmnopqrstuvwxyz'''
upperCaseLetters = '''ABCDEFGHIJKLMNOPQRSTUVWXYZ'''
digits = '''123456789'''
specialCharacters = '''!()-[]{};:'"\,./?@#$%^&*_~'''

numOfChars = int(input("What is the length of the desired password: "))

print("Please say YES or NO to the following:")

isSpecialCharNeeded = input("Would you like special characters in your password? ")

totalPassword = lowerCaseLetters + upperCaseLetters + digits

if(isSpecialCharNeeded.lower()=="yes"):
    totalPassword = lowerCaseLetters + upperCaseLetters + digits + specialCharacters

finalPassword = "".join(random.sample(totalPassword,numOfChars))
print("Your newly generated password is " + finalPassword)
