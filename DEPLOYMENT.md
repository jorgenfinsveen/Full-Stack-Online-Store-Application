# Note regarding the deployment
---

When opening the website in a browser such as Google Chrome, one may experience rceiving the following error in the console:

```s
net::ERR_CERT_AUTHORITY_INVALID
```

This is because the website forwards the requests to the back-end directly to port 8080 on the server, rather than using the forwarding proxy which we have implemented on the server. The error occurs because the SSL certificate used in the Spring Boot application is self signed and not recognized by a CA.

Some browsers may therefore prevent the requests being sent. If this were to be fixed by the developers, they would set up an SSL certificate for the default port of the OpenStack server, and forward requests to port 8080. The front-end is now sending their requests directly to port 8080 in order to make use of the SSL certificate implemented for the Spring Boot application, so this would also be changed.


Should you experience receiving the error mentioned above, we would recommend the following:

* Ensure that you are connected to NTNUs network
* Try to disable validation of SSL certificate authority in your browser if possible
* Try opening the website in another browser, such as Safari


## Workaround

In order to override the SSL certificate validation configuration in a browser, do the following:

* Open the following link in the same browser: https://group10.web-tek.ninja:8080/products
* Click "Trust domain"
* Now, the products should be visible in the website
