package com.vach.cafe.server.exception;

/**
 * Tab can be opened only once, on any consecutive attempt to open it
 * again this exception shall be thrown.
 */
public class TabIsOpen extends CommandException {

}
