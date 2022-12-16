package com.figo.services.photo;

import com.figo.criteria.PhotoCriteria;
import com.figo.criteria.UserCriteria;
import com.figo.dtos.photos.PhotoDTO;
import com.figo.response.DataDTO;
import com.figo.response.Response;
import com.figo.services.base.GenericCrudService;

import javax.servlet.http.HttpSession;
import java.net.http.HttpRequest;

public interface PhotoService extends GenericCrudService<PhotoDTO, PhotoDTO, PhotoDTO, String, PhotoCriteria> {
}
