package com.devteria.notification.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

/**
 * @author DatNv
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EmailResponse {
    String messageId;
}
