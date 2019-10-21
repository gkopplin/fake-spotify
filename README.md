# fake-spotify

# Design Decisions
- Many to many relationship for Songs to Users
- JWT for Authentication
- Admin user role
    - Used to restrict access to roles and delete users
- Endpoints for users' songs listed under User
    - Differentiate between createSong endpoint and songs for a specific user
    - Able to pass user Id as params
- One to many relationship for Users to UserRoles
- Users' songs stored in User instance variable
    - Easy access and avoids unnecessary "profile" class

# Challenges 
- Mocking database queries
- Not as much time for pair programming
