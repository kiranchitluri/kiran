package cz.siret.prank.features.implementation.sequence

import cz.siret.prank.domain.Residue
import cz.siret.prank.features.api.ResidueFeatureCalculationContext
import cz.siret.prank.features.api.ResidueFeatureCalculator
import cz.siret.prank.features.tables.PropertyTable
import cz.siret.prank.program.params.Parametrized
import groovy.transform.CompileStatic
import groovy.util.logging.Slf4j

import javax.annotation.Nullable

import static cz.siret.prank.utils.Futils.readResource

/**
 * Sequence duplet propensities for closest residue
 *
 * For propensity calculation
 * @see cz.siret.prank.program.routines.AnalyzeRoutine#cmdAaSurfSeqTriplets()
 */
@Slf4j
@CompileStatic
class TripletsPropensityFeature extends ResidueFeatureCalculator implements Parametrized {

    static final String PROPERTY = 'propensity'
    PropertyTable table 

    //static List<String> HEADER = ['prop', 'prop^2']
    static List<String> HEADER = ['prop']

//===========================================================================================================//

    @Override
    String getName() {
        'triplets'
    }

    @Override
    List<String> getHeader() {
        HEADER
    }

    PropertyTable getTable() {
        if (table == null) {
            table = PropertyTable.parse(
                    readResource("/tables/peptides/$params.pept_propensities_set/triplets.csv"))
        }
        table
    }

    @Override
    double[] calculateForResidue(Residue residue, ResidueFeatureCalculationContext context) {
        double prop = calculatePropensityForResidue(residue)

        //return [prop, prop*prop] as double[]
        return [prop] as double[]
    }

    double calculatePropensityForResidue(@Nullable Residue res) {
        String code = Residue.safeSorted3CodeFor(res)
        double prop = getTable().getValueOrDefault(code, PROPERTY, 0d)

        return prop
    }
    
}
