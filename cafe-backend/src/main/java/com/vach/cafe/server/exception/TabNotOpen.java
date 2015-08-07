package com.vach.cafe.server.exception;

/**
 * In order to execute some commands on a Tab it is
 * required to be opened, if its not this exception
 * is expected to be thrown.
 */
public class TabNotOpen extends CommandException {

}
