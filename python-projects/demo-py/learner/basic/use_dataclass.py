from dataclasses import dataclass

@dataclass
class Employee:
    name: str
    emp_id: int
    email_id: str

emp = Employee('Rohit', 123456, 'rohit@abc.com')

print(emp.name, emp.emp_id, emp.email_id)