package br.com.kleryton.magicthegathering.models;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;

//informa que esta classe é uma entidade
@Entity
@Table(name = "TB_CARDS")
public class Player implements Serializable {
	private static final long serialVersionUID = 1L;

}
