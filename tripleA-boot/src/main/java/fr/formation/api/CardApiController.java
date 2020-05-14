package fr.formation.api;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.formation.dao.IDAOCard;
import fr.formation.model.Card;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/card")
public class CardApiController {
	@Autowired
	private IDAOCard daoCard;
	
	@GetMapping
	public List<Card> findAll() {
		return this.daoCard.findAll();
	}
	
	@GetMapping("/{id}")
	public Card findById(@PathVariable int id) {
		return this.daoCard.findById(id).orElse(new Card());
	}
	
	@PostMapping
	public Card add(@Valid @RequestBody Card card, BindingResult result) {
		this.daoCard.save(card);
		return card;
	}
	
	@PutMapping("/{id}")
	public Card findById(@PathVariable int id, @RequestBody Card card) {
		this.daoCard.save(card);
		return card;
	}
	
	@DeleteMapping("/{id}")
	public boolean delete(@PathVariable int id) {
		try {
		this.daoCard.deleteById(id);
		}
		catch(Exception e) {
			return false;
		}
		return true;
	}

}
