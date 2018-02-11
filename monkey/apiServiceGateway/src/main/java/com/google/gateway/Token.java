package com.google.gateway;

import lombok.Data;

/**
 * Token信息
 * 
 * @author
 *
 */
@Data
public class Token {
    private Long 	id;
    private String 	mobile;
    private String 	username;
    private Long 	loginTime;
}
