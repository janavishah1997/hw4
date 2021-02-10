# Task 1

The SOLID design principles that the advisor adheres to are : 
- Dependency inversion principle
- Open Close Principle

Adding a interface between "Shelf" class and "Book" , "DVD" splits the dependency between high level and low level classes.
The high level class is : Shelf
The low level classes are "Books" and "DVD".
By applying this principle, even if there are changes in the lower level classes, the Shelf class will not be affected. 

The design also allows us to extensions with out depending on the high leveel class "Shelf" , therefore, it also follows a open/close principle.
The design is open to extension and closee to modification.
