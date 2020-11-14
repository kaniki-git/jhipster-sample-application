package com.mycompany.myapp.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.mycompany.myapp.web.rest.TestUtil;

public class TabParamVitauxTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TabParamVitaux.class);
        TabParamVitaux tabParamVitaux1 = new TabParamVitaux();
        tabParamVitaux1.setId(1L);
        TabParamVitaux tabParamVitaux2 = new TabParamVitaux();
        tabParamVitaux2.setId(tabParamVitaux1.getId());
        assertThat(tabParamVitaux1).isEqualTo(tabParamVitaux2);
        tabParamVitaux2.setId(2L);
        assertThat(tabParamVitaux1).isNotEqualTo(tabParamVitaux2);
        tabParamVitaux1.setId(null);
        assertThat(tabParamVitaux1).isNotEqualTo(tabParamVitaux2);
    }
}
