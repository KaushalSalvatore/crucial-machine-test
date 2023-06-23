# crucial-machine-test
Crucial technologies machine test

1. Call api to show categories in this way:
   Category -> Sub cat-> sub-sub cat -> item

Show it into List and Grid view via api:-
a) Service with section API -

https://www.utzo.com/api/other?func=section_with_services&latitude=22.724470&longitude=75.905115&from=home


      b) Service with sub category API -
         https://www.utzo.com/api/other?func=service&service_id=3

*Note: For Api Reference, we are sharing Design screenshots. Please find
the attachment.

2. API implenation of Lazy loading.
   https://www.utzo.com/api/technician?func=project_history&account_id=24&page=1


3. Map direction/tracking
   need to draw a polyline between two points i.e.  source and destination.
   And if we move the device, the map marker will start moving accordingly.
