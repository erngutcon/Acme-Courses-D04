package acme.spam;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Range;

import acme.framework.datatypes.AbstractDatatype;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter
@Setter
@AllArgsConstructor
public class SpamRecord extends AbstractDatatype  {
	
	
	// Serialisation identifier -----------------------------------------------

	protected static final long serialVersionUID = 1L;

	// Attributes -------------------------------------------------------------

	@NotBlank
	protected String term;

	@NotNull
	@Range(min = 0, max = 1)
	protected Double weight;

	protected String booster;
	

}
