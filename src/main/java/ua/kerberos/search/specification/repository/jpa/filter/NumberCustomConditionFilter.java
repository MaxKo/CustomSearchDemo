package ua.kerberos.search.specification.repository.jpa.filter;

import jakarta.persistence.criteria.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Maksym Kovieshnikov on 02/06/2020
 */

public class NumberCustomConditionFilter extends AbstractJpaFilter {
    public static final String GREATER = ">";
    public static final String LESS = "<";
    private final Pattern patternDigital = Pattern.compile("\\d+");

    @Override
    public Predicate getPredicate() {
        String strValue = this.value.toString();

        String condition = strValue.substring(0, 1);
        Matcher matcher = this.patternDigital.matcher(strValue);
        matcher.find();
        String val = matcher.group();

        switch (condition) {
            case GREATER:
                return criteriaBuilder.greaterThan(this.root.get(this.propertyName), val);
            case LESS:
                return criteriaBuilder.lessThan(this.root.get(this.propertyName), val);
            default:
                return this.criteriaBuilder.equal(this.root.get(this.propertyName), val);
        }
    }
}
