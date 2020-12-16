package ru.job4j.serialization;


import java.io.*;
import java.nio.file.Files;

public class Contact implements Externalizable {
  private static final long serialVersionUID = 1L;
  private int zipCode;
  private String phone;

  public Contact() {
    this.zipCode = 0;
    this.phone = "";
  }

  public Contact(int zipCode, String phone) {
    this.zipCode = zipCode;
    this.phone = phone;
  }

  public int getZipCode() {
    return zipCode;
  }

  public String getPhone() {
    return phone;
  }

  @Override
  public String toString() {
    return "Contact{"
      + "zipCode=" + zipCode
      + ", phone='" + phone + '\''
      + '}';
  }

  public static void main(String[] args) throws IOException, ClassNotFoundException {
    final Contact contact1 = new Contact(123456, "+7 (111) 111-11-11");
    final Contact contact2 = new Contact(789101, "+7 (222) 222-22-22");
    /* Запись объекта в файл */
    File tempFile = Files.createTempFile(null, null).toFile();
    try (FileOutputStream fos = new FileOutputStream(tempFile);
         ObjectOutputStream oos =
           new ObjectOutputStream(fos)) {
      oos.writeObject(contact1);
      oos.writeObject(contact2);
    }

    /* Чтение объекта из файла */
    try (FileInputStream fis = new FileInputStream(tempFile);
         ObjectInputStream ois =
           new ObjectInputStream(fis)) {
      Contact contact1FromFile = (Contact) ois.readObject();
      Contact contact2FromFile = (Contact) ois.readObject();
      System.out.println(contact1FromFile);
      System.out.println(contact2FromFile);
    }
  }

  @Override
  public void writeExternal(ObjectOutput out) throws IOException {
    out.writeObject(this.zipCode);
    out.writeObject(this.phone);
  }

  @Override
  public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {

    zipCode = (int) in.readObject();
    phone = (String) in.readObject();
  }
}

