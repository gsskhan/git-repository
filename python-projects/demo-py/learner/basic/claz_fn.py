# -*- coding: utf-8 -*-
"""
Example to check classes
"""

import sys


class MyClass:

    def __init__(self, fruit_name):
        self.fname = fruit_name

    def slice_fruit(self):
        print ("Made fruit slice of", self.fname)

def fn_make_custard(fr):
    print ("Made custard with", fr)

apple = MyClass("apple")
apple.slice_fruit()

banana = MyClass("banana")
banana.my_custard = fn_make_custard      # Dynamic assigned function to class instance object (Bad coding)
banana.my_custard('cherry')
banana.brand = "alpino"                  # Dynamic assigned an field to class instance object (its Ok)

print("Attributes of 'banana' object:", dir(banana))
