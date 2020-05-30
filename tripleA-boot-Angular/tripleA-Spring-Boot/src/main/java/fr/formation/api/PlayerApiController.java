package fr.formation.api;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.formation.dao.IDAOPlayer;
import fr.formation.model.Human;
import fr.formation.model.Player;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/player")
public class PlayerApiController {
	
	@Autowired
	private IDAOPlayer daoPlayer;
	
	@GetMapping
	public List<Player> findAll(){
		return this.daoPlayer.findAll();
	}
	
	@GetMapping("/{id}")
	public Player findById(@PathVariable int id	) {
		return this.daoPlayer.findById(id).orElse(new Human());
	}
	
	@PostMapping("/{id}")
	public Player add(@Valid @RequestBody Player player) {
		return player;
	}
	
	@PutMapping("/{id}")
	public Player update(@PathVariable int id, @RequestBody Player player) {
		player.setId(id);
		return this.daoPlayer.save(player);
	}
	
	@DeleteMapping("/{id}")
	public boolean delete(@PathVariable int id) {
		try {
			this.daoPlayer.deleteById(id);
		} catch (Exception e) {}
		return true;
	}
	
	
	
	
	
	
	
	
	
	
}
