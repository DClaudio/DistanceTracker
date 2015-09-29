package com.distancetracker.api

/**
 * Created by claudio.david on 29/09/2015.
 */
class GPSApiTest extends BaseServletTest {

  // implicit var mockDeviceDao: GPSDao = mock[GPSDao]

  addServlet(new GPSApi, "/gps-data/*")


}
