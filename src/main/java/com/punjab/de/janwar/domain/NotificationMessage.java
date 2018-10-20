package com.punjab.de.janwar.domain;

import lombok.*;

@AllArgsConstructor
@Getter
@NoArgsConstructor
@Setter
@ToString
public class NotificationMessage {

    private String name;
    private String id;
    private String desc;
    private String address;
    private int count;
}
