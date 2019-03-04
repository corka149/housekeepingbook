package org.corka.housholdkeepingbook.domain.user;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
class UserAlreadyExistsException extends Throwable {
}
