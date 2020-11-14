package com.mycompany.myapp.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.mycompany.myapp.web.rest.TestUtil;

public class TabGynecologieTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TabGynecologie.class);
        TabGynecologie tabGynecologie1 = new TabGynecologie();
        tabGynecologie1.setId(1L);
        TabGynecologie tabGynecologie2 = new TabGynecologie();
        tabGynecologie2.setId(tabGynecologie1.getId());
        assertThat(tabGynecologie1).isEqualTo(tabGynecologie2);
        tabGynecologie2.setId(2L);
        assertThat(tabGynecologie1).isNotEqualTo(tabGynecologie2);
        tabGynecologie1.setId(null);
        assertThat(tabGynecologie1).isNotEqualTo(tabGynecologie2);
    }
}
