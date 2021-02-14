package com.development.loginmvvm.data.network

import java.io.IOException
import java.net.UnknownHostException

class ApiException(message: String) : IOException(message)
class NoInternetException(message: String) : UnknownHostException(message)