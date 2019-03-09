# wonderQ

API Documentation

Documentation for existing API endpoints:

The endpoint ‘/messages’ returns a list of JSONs of all messages existing in the database. The only parameter is pageable (which is not currently used), allowing the option to specify and return a select number of pages rather than list. 

The endpoint ‘messages/new’ allows users to view all messages with the field wasProcessed set to false (essentially every message that is open for consumption). A list of objects in the JSON format is returned. The only parameter is pageable (which is not currently used), allowing the option to specify and return a select number of pages rather than list. 

The POST endpoint ‘/messages’ allows users to create/publish a new message. The parameter is the message object being created. If the incoming message has an ID of 0, the status field of the message is set to ‘UNREAD’, and the message is saved in the database. The id of the newly saved message is returned.

The PUT endpoint ‘/messages/{id} allows users to update a message. It takes the updated message object as a parameter and saves it to the database. A status consisting of the success code (true/false), generated Id, message, and potential error message is returned.

The DELETE endpoint ‘/messages/{id}’ allows users to delete a message. It takes the message to be deleted’s id as a parameter and returns a status consisting of the status code (true/false), generated Id if successful, and error message if not.

The ‘deleteAllProcessedMessages()’ function runs at a fixed rate of every 30 minutes. It iterates through all messages and deletes the ones with a status set to ‘PROCESSED’.

Documentation for potential API endpoints:

The endpoint ‘/messages/{id}’ would return the message with the specified id. The parameter would be the messageId. 

The endpoint ‘/messages/today’ would return all messages published today. The parameter would be today’s date and the function would search for and return a list of messages with publish date set to today.

The endpoint ‘/messages/betweenDates/{date1}/{date2}’ would return all unprocessed messages published between two selected dates. The parameters would be date1 and date2, and the query would search for all messages published between those dates.

How would I go about scaling this system to meet high-volume requests?

For larger scale production I would create a user object to save all users to the database and endpoints would be restricted to secured/registered users. Also, I might add a userId field to the message object in order to keep track of which user processed which message (and not delete unprocessed messages from the database). 
I might also add more meditada to the message obj (such as date and time consumed and subject of message)? In terms of larger scale production I think it would be prudent to organize questions according to subject and allow researchers to search based on subject, and potentially, his/her expertise.


