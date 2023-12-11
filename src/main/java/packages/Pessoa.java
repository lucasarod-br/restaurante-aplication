/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main.java.packages;

/**
 *
 * @author lalve
 */
import java.util.Date;

public interface Pessoa {
    String getId();
    void setId(String id);

    String getCpf();
    void setCpf(String cpf);

    String getNome();
    void setNome(String nome);

    Date getDataNascimento();
    void setDataNascimento(Date dataNascimento);

    boolean isStatus();
    void setStatus(boolean status);

    String getUserName();
    void setUserName(String userName);

    String getEmail();
    void setEmail(String email);
}
