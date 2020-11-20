# Application Setup

Backend is running on port 7776 on remote server at /home/vergo/anisong

Frontend is running on port 7777 on remote server - nginx is serving up index.html + the other static angular files

Nginx config is located in /etc/nginx/sites-enables, file is default 

Any requests to frontend of the form /api/blah are proxied to the server running on 7776, with the "api" bit
stripped out so that the request becomes /blah 

In vergo home folder there is a script called deployServer.sh, can use that to redeploy server after changes 

# Deployment process

## Server

- Make changes as needed
- Run gradle task "bootJar"
- Copy jar from /build/libs to the remote directory /home/vergo/anisong/server
- Run script ```deployServer.sh```

## UI 

- Make changes as needed
- Run ```ng build --prod```
- Copy *all* contents from generated "dist" folder to /home/vergo/anisong
- To restart nginx, run ```service nginx restart```

# Good to know

- Data is pulled from google calendar using the API, for this an API key is needed which is generated at https://console.developers.google.com/apis/credentials?project=anisong
- The API key should be kept secret and hence isn't checked into codebase, it's instead passed as a system property during application startup
- API docs for Calendar events are located here https://developers.google.com/calendar/v3/reference/events#resource 

# Local testing

- Server runs by default on port 8080
- UI runs by default on port 4200
- ```proxy.conf.json``` defined on UI side proxies any **local** requests made to localhost:4200/api/blah to localhost:8000/blah, thus simulating what happens on actual remote 
- Hence for local testing, you should run server on 8080 and serve up UI on 4200