package com.mycompany.myapp.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.mycompany.myapp.web.rest.TestUtil;

public class TabBiologieTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TabBiologie.class);
        TabBiologie tabBiologie1 = new TabBiologie();
        tabBiologie1.setId(1L);
        TabBiologie tabBiologie2 = new TabBiologie();
        tabBiologie2.setId(tabBiologie1.getId());
        assertThat(tabBiologie1).isEqualTo(tabBiologie2);
        tabBiologie2.setId(2L);
        assertThat(tabBiologie1).isNotEqualTo(tabBiologie2);
        tabBiologie1.setId(null);
        assertThat(tabBiologie1).isNotEqualTo(tabBiologie2);
    }
}
