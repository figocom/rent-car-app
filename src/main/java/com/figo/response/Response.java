package com.figo.rentcar.response;

import java.io.Serializable;

public record Response<T extends Serializable>(T data) { }
