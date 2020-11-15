package com.mycompany.myapp.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.mycompany.myapp.web.rest.TestUtil;

public class TabRefFonctionTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TabRefFonction.class);
        TabRefFonction tabRefFonction1 = new TabRefFonction();
        tabRefFonction1.setId(1L);
        TabRefFonction tabRefFonction2 = new TabRefFonction();
        tabRefFonction2.setId(tabRefFonction1.getId());
        assertThat(tabRefFonction1).isEqualTo(tabRefFonction2);
        tabRefFonction2.setId(2L);
        assertThat(tabRefFonction1).isNotEqualTo(tabRefFonction2);
        tabRefFonction1.setId(null);
        assertThat(tabRefFonction1).isNotEqualTo(tabRefFonction2);
    }
}
