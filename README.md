# Multicast-Application

## How to Run 
Clone the project on your machine using the ```git clone``` command.
Open it in your preferred IDE and you can run it normally:
- To start the server, execute the Academia class, and to start the clients of the academy, execute the Cliente class.

- To send messages like the Academia, you must select the channel to which you want to send the message for each new message. 
  - Type option 1 to speak in the information channel, press enter, write the message, and press enter again to send it.
  - Type option 2 to speak in the promotions channel, press enter, write the message, and press enter again to send it.

- To subscribe to a message channel as a Client, you must select one of the 3 options available to you and provide your name.
  - Select the first option to speak in the information channel, press enter, write the message, and press enter again to send it.
  - Select the second option to receive messages in the promotions channel; in this channel, you will not be able to send messages.
  - Select the third option to subscribe to both channels, press enter, write the message, and press enter again to send it.

**Note**: We are using pop-ups in our system, both at the beginning of the client and when receiving messages; on some operating systems, the pop-ups do not appear automatically, and you need to click on them to view the message.

## A Little About the Project
### System Context: Gym 🏋️
It is a service for sending and receiving messages on topics using Multicast sockets developed for a gym, for:

(Topic 1) Promotion dissemination
(Topic 2) Notices about specific classes, such as Fit Dance, Zumba, abdominal, endurance, etc.

### Message Format
[dd/MM/yyyy - HH:mm] - topic_name - name: Body
