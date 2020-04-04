### Rent-a-book

- Rent a book is a service which lends books to customers for a specific period of time.
- There can be multiple branches for this service.
- Every branch will have a branch admin.
- Each branch has a collection of books to lend.
- Customer should be able to 
    - check if a particular book is available at a branch. 
    - get the catalogue of all the books of a branch. 
    - get details of the branch at which a particular book is available. 
    - reserve a book. A reserved book which is unclaimed for more than 2 days is automatically unreserved. 
    - see all his borrowed books, reserved books. 
    - create wish lists of books he would like to read next.
    - opt for notification of when a book will be available at a branch.
- Admin should be able to
    - see the list of all customers.
    - see the list of all books.
    - see the list of all books in a particular branch.
    - see the list of all borrowed books.
    - see the list of all borrowed books by branch.
    - see the list of all reserved books.
    - see the list of all reserved books by branch.
    - allocate a book to a customer. A customer can have only 2 books with him at any given point of time.
    - release a book from customer.
   
##### Notifications
- System should notify the admin and the customer about the expiry of his borrowed book time.
- System should notify the customer when a book he requested at a branch becomes available.

##### Analytics
- Generate metrics on top 5 borrowed books across all branches.
- Generate metrics on top 5 borrowed books at a given branch.
- Generate metrics on top 5 borrowers across all branches.
- Generate metrics on top 5 borrowers at a given branch.
- Generate metrics on top 3 branches according to books lent.

    