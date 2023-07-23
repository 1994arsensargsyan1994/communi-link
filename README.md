# Generic Booking Application

The Generic Booking Application is a project that utilizes the Communi-Link
public API to create and manage communities based on different types, make reservations, and handle cancellations.
The application allows users to interact with the Communi-Link API to perform various actions related to community management and bookings.


Here's a brief overview of the main functionalities of the Generic Booking Application:

- Community Creation:Users can create new communities by providing relevant information such as the community name, 
type, and maximum count of members allowed in the community. The application interacts with the Communi-Link API to create the community using the provided data.

- Community Types: Communities can be categorized into different types, each with specific characteristics and 
purposes. The application allows users to specify the type of the community during creation.

- Reservation: Users can make reservations for community activities or events. The application communicates with the 
Communi-Link API to process reservation requests and checks for availability based on the community's current count.

- Cancellation: In case users need to cancel their reservations, the application provides a functionality to handle 
cancellations and updates the community's current count accordingly.

- Community Lookup: The application has features to look up existing communities, retrieve their details, and display 
 them to users.

- Resident Lookup: It offers features to search for residents or users within a community based on their usernames or 
other identifiers.

- Authentication: The application may include an authentication mechanism to ensure that only authorized users can 
 perform actions related to community management and bookings.

- Error Handling: Proper error handling is implemented to provide meaningful error messages to users in case of any 
 issues or failures during API interactions.

Overall, the Generic Booking Application serves as an interface to the Communi-Link public API, allowing users to interact with the API's endpoints related to community creation, reservation, and cancellation. The application's user-friendly interface simplifies the process of managing communities and bookings, making it easier for users to use the Communi-Link services effectively.
# How to run 

# Generic Booking Application with Keycloak Authentication

This project is a Generic Booking Application that uses Keycloak for user authentication. Keycloak is an open-source Identity and Access Management (IAM) solution that provides Single Sign-On (SSO) and identity management capabilities.

## Getting Started

To run the Generic Booking Application with Keycloak authentication, follow these steps:

1. Install Docker on your machine (if not already installed).

2. Pull and run the Keycloak Docker image:

   ```bash
   docker run -p 8080:8080 -e KEYCLOAK_USER=admin -e KEYCLOAK_PASSWORD=admin quay.io/keycloak/keycloak:22.0.1

This command will start the Keycloak server, and you can access the Keycloak Admin Console at: http://localhost:8080/auth/admin/

- Create a Realm (communi):

In the Keycloak Admin Console, click on "Add realm" to create a new realm.
Enter "communi" as the name for the new realm and click "Create."

- Create a Client (dev):
Within the "communi" realm, click on "Clients" in the left sidebar.
Click "Create" to add a new client.
Enter "dev" as the Client ID, leave the other settings as default, and click "Save."

- Create a User (communi-dev) with Password Grant Type:

Still within the "communi" realm, click on "Users" in the left sidebar.
Click "Add user" to create a new user.
Fill in the necessary details for the user. For example, set the username as "communi-dev."
Go to the "Credentials" tab, set a temporary password, and enable "Temporary" to force the user to change the password on the first login.
Go to the "Role Mappings" tab and assign the "dev" client role to the user.
Click "Save" to create the user.

- Create the "COMMUNITY_PORTAL" Role for the "dev" Client:
Within the "communi" realm, click on "Clients" in the left sidebar.
Select the "dev" client to manage its roles.
Click on the "Add Role" button.
Enter "COMMUNITY_PORTAL" as the Role Name and click "Save."

- For more details on setting up and using Keycloak, refer to the official Keycloak documentation:
- [Getting Started with Keycloak Docker](https://www.keycloak.org/getting-started/getting-started-docker)


1. Pull and run the postgresql Docker image:

   ```bash
   docker run -d --name communi_db -p 5432:5432 -e POSTGRES_DB=communi_link -e POSTGRES_USER=postgres -e POSTGRES_PASSWORD=postgres postgres:latest

- and create a database named "communi_link":

After running just check the swagger ui with openapi documentation.
[API docs] (http://localhost:8081/swagger-ui/index.html#/)

I advise  use
[For Simulation] Open the `rest/simulation.http` file.