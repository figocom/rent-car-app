package com.figo.response;

import java.io.Serializable;

public record Response<T extends Serializable>(T data) { }
