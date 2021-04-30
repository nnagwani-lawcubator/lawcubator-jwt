package com.lawcubator.notification.service.mapper;

import com.lawcubator.notification.domain.*;
import com.lawcubator.notification.service.dto.NotificationDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Notification} and its DTO {@link NotificationDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface NotificationMapper extends EntityMapper<NotificationDTO, Notification> {}
