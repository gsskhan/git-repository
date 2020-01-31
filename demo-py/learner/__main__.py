# -*- coding: utf-8 -*-

import sys
import os

def _display_initial_stmts():
    print("PYTHONPATH :" + str(sys.path))
    print("PYTHON binary location :"+sys.executable)
    pathname = os.path.dirname(sys.argv[0])
    print("Script location :"+ os.path.abspath(pathname))


if __name__ == "__main__":
    _display_initial_stmts()
    print("application completed.")
