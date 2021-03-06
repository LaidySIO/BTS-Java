package inscriptions;

import java.util.Collections;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * Représente une Equipe. C'est-à-dire un ensemble de personnes pouvant 
 * s'inscrire à une compétition.
 * 
 */

public class Equipe extends Candidat
{
	private static final long serialVersionUID = 4147819927233466035L;
	private SortedSet<Personne> membres = new TreeSet<>();
	boolean db= true;
	private Personne entraineur;

	Equipe(Inscriptions inscriptions, String nom)
	{
		super(inscriptions, nom);
	}
	Equipe (Inscriptions inscriptions, String nom, Personne Entraineur)
	{
		this (inscriptions, nom);
		this.entraineur = Entraineur;
	}
	public Personne getEntraineur()
	{
		return this.entraineur;
	}
	public void setEntraineur (Personne p)
	{
		this.entraineur = p;
	}

	/**
	 * Retourne l'ensemble des personnes formant l'équipe.
	 */
	
	public SortedSet<Personne> getMembres()
	{
		
		return Collections.unmodifiableSortedSet(membres);
	}
	
	/**
	 * Ajoute une personne dans l'équipe.
	 * @param membre
	 * @return
	 */

	public boolean add(Personne membre)
	{
		membre.add(this);
		return membres.add(membre);
	}

	/**
	 * Supprime une personne de l'équipe. 
	 * @param membre
	 * @return
	 */
	
	public boolean remove(Personne membre)
	{
		membre.remove(this);
		return membres.remove(membre);
	}

	@Override
	public void delete()
	{
		if (db)
			DB.Req.suppEqui(this.getNom());
		super.delete();
	}
	
	@Override
	public String toString()
	{
		return "Equipe " + super.toString();
	}
}
