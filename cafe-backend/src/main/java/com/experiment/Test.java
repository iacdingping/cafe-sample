package com.experiment;

import com.experiment.AddressBookProto.Person;

import java.io.IOException;

public class Test {

  public static void main(String[] args) throws IOException {
    Person john =
        Person.newBuilder()
            .setId(1234)
            .setName("John Doe")
            .setEmail("jdoe@example.com")
            .addPhone(
                Person.PhoneNumber.newBuilder()
                    .setNumber("555-4321")
                    .setType(Person.PhoneType.HOME))
            .build();


  }
}
