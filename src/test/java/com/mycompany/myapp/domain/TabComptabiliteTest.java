package com.mycompany.myapp.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.mycompany.myapp.web.rest.TestUtil;

public class TabComptabiliteTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TabComptabilite.class);
        TabComptabilite tabComptabilite1 = new TabComptabilite();
        tabComptabilite1.setId(1L);
        TabComptabilite tabComptabilite2 = new TabComptabilite();
        tabComptabilite2.setId(tabComptabilite1.getId());
        assertThat(tabComptabilite1).isEqualTo(tabComptabilite2);
        tabComptabilite2.setId(2L);
        assertThat(tabComptabilite1).isNotEqualTo(tabComptabilite2);
        tabComptabilite1.setId(null);
        assertThat(tabComptabilite1).isNotEqualTo(tabComptabilite2);
    }
}
