package fr.formation.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity 
@DiscriminatorValue("0")
public class AI extends Player {

	public AI(int id, String name, Card carte1, Card carte2, Card carte3, boolean typePlayer, int idOpponent) {
		super(id, name, carte1, carte2, carte3, typePlayer, idOpponent);
	}

	public AI(Card carte1, Card carte2, Card carte3) {
		super(carte1, carte2, carte3);
		this.typePlayer = false;
		this.name = "AI";
	}

	public AI() {
		this.typePlayer = false;
		this.name = "AI";
	}

	public int choiceDistance() {
		int card = 0;
		List<Integer> l = new ArrayList<>();

		l.add(this.getCard1().getAtk());
		l.add(this.getCard2().getAtk());
		l.add(this.getCard3().getAtk());

		Collections.sort(l, Collections.reverseOrder());

		if (l.get(0) == this.getCard1().getAtk()) {
			this.getCard1().setPosition(true);
			card = 1;
		} else if (l.get(0) == this.getCard2().getAtk()) {
			this.getCard2().setPosition(true);
			card = 2;
		} else if (l.get(0) == this.getCard3().getAtk()){
			this.getCard3().setPosition(true);
			card = 3;
		}

		return card;
	}

	public String attack(List<Card> deckH, List<Card> deckAI, Human h, AI ai, int att) {
		int target = 0;
		Boolean result;
		int aidist = ai.getCardDistance(deckAI);
		int hdist = h.getCardDistance(deckH);
		int hndist1, hndist2;
		if(hdist==1) {hndist1=1;hndist2=2;}
		else if(hdist==2) {hndist1=2;hndist2=0;}
		else {hndist1=0;hndist2=1;}


		do {target = ai.RNG(3);
		System.out.println("target du rng=c"+(target+1));
		result = false;
		if (deckH.get(target).getLife() > 0) {
			if(aidist!=(att+1) && deckH.get(hndist1).getLife() <= 0 && deckH.get(hndist2).getLife() <= 0) {result=true;}
			else if(aidist==(att+1)) {result=true;}
			else if(aidist!=(att+1) && hdist!=(target+1)) {result=true;}
		}
		else {result=false;}
		}while(!result);


		int dmg=deckH.get(target).isAttackedBy(deckAI.get(att),1);
		h.setDmgTaken(dmg+h.getDmgTaken());
		System.out.println("aicard" + (att+4) + " attaque hcard" + (target+1));
		
		String msg="<p>c"+(att+4)+" inflige <span style='color:#FF0000'>"+dmg+"</span> dégats à c"+(target+1)+"</p>";

		return msg;
	}

	public String bossAttack(List<Card> deckH, List<Card> deckAI, Human h, AI ai, int turn) {
		String msg="";
		double tour=(turn+2)/3;
		int target = 0;
		Boolean result;
		int hdist = h.getCardDistance(deckH);
		int hndist1, hndist2;
		if(hdist==1) {hndist1=1;hndist2=2;}
		else if(hdist==2) {hndist1=2;hndist2=0;}
		else {hndist1=0;hndist2=1;}
		int dmg1=0, dmg2=0, dmg3=0;
		String msg1="", msg2="", msg3="";

		if((tour+2)%3==0) {
			do {target = ai.RNG(3);
			result = false;
			if (deckH.get(target).getLife() > 0) {
				if(deckH.get(hndist1).getLife() <= 0 && deckH.get(hndist2).getLife() <= 0) {result=true;}
				else if(hdist!=(target+1)) {result=true;}}
			else {result=false;}
			}while(!result);
			dmg1=deckH.get(target).isAttackedBy(deckAI.get(0),1);
			h.setDmgTaken(dmg1+h.getDmgTaken());
			msg1="<p>Black Jordan inflige <span style='color:#FF0000'>"+dmg1+"</span> dégats à c"+(target+1)+"</p>";
			msg=msg1+"<p>Black Jordan donne un <span style='color:#7F00FF'>Coup de poing ordinnaire</span></p>";
		}
		else if((tour+1)%3==0) {
			if(deckH.get(hndist1).getLife() <= 0 && deckH.get(hndist2).getLife() <= 0) {
				dmg1=deckH.get(hdist-1).isAttackedBy(deckAI.get(0),1);
				msg1="<p>Black Jordan inflige <span style='color:#FF0000'>"+dmg1+"</span> dégats à c"+(hdist)+"</p>";
			}
			else {
				if(deckH.get(hndist1).getLife() > 0) {
					dmg1=deckH.get(hndist1).isAttackedBy(deckAI.get(0),0.66);
					msg1="<p>Black Jordan inflige <span style='color:#FF0000'>"+dmg1+"</span> dégats à c"+(hndist1+1)+"</p>";}
				if(deckH.get(hndist2).getLife() > 0) {
					dmg2=deckH.get(hndist2).isAttackedBy(deckAI.get(0),0.66);
					msg2="<p>Black Jordan inflige <span style='color:#FF0000'>"+dmg2+"</span> dégats à c"+(hndist2+1)+"</p>";}
			}
			h.setDmgTaken(dmg1+dmg2+h.getDmgTaken());
			msg=msg2+msg1+"<p>Black Jordan fait une <span style='color:#7F00FF'>Onde de choc</span></p>";
		}
		else if((tour)%3==0) {
			
			if(deckH.get(hndist1).getLife() > 0) {
				dmg1=deckH.get(hndist1).isAttackedBy(deckAI.get(0),0.5);
				msg1="<p>Black Jordan inflige <span style='color:#FF0000'>"+dmg1+"</span> dégats à c"+(hndist1+1)+"</p>";}
			if(deckH.get(hndist2).getLife() > 0) {
				dmg2=deckH.get(hndist2).isAttackedBy(deckAI.get(0),0.5);
				msg2="<p>Black Jordan inflige <span style='color:#FF0000'>"+dmg2+"</span> dégats à c"+(hndist2+1)+"</p>";}
			if(deckH.get(hdist-1).getLife() > 0) {
				dmg3=deckH.get(hdist-1).isAttackedBy(deckAI.get(0),0.5);
				msg3="<p>Black Jordan inflige <span style='color:#FF0000'>"+dmg3+"</span> dégats à c"+(hdist)+"</p>";}
			h.setDmgTaken(dmg1+dmg2+dmg3+h.getDmgTaken());
			msg=msg3+msg2+msg1+"<p>Black Jordan lance <span style='color:#7F00FF'>Pluie de météors</font></p>";
		}

		return msg;
	}


	public void protection() {
		List<Card> deckAI = this.deck();
		int p = this.getCardProtected(deckAI), protectionAI = 0;
		if(deckAI.size()==3) {
			do {
				protectionAI = this.RNG(3);
			} while(protectionAI == p-1);
			if(p!=0) {
				deckAI.get(p-1).setProtection(false);}
			deckAI.get(protectionAI).setProtection(true);
		}
		else if(deckAI.size()==2) {
			if(p==1) {
				protectionAI=1;
			}
			else {protectionAI=0;}
			if(p!=0) {
				deckAI.get(p-1).setProtection(false);}
			deckAI.get(protectionAI).setProtection(true);
		}
		else {deckAI.get(p-1).setProtection(false);}
		System.out.println("L'ia protege card" + protectionAI+" et p = "+p);
	}

}
