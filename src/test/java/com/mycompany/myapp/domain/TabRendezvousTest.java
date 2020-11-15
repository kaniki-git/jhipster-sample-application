package com.mycompany.myapp.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.mycompany.myapp.web.rest.TestUtil;

public class TabRendezvousTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TabRendezvous.class);
        TabRendezvous tabRendezvous1 = new TabRendezvous();
        tabRendezvous1.setId(1L);
        TabRendezvous tabRendezvous2 = new TabRendezvous();
        tabRendezvous2.setId(tabRendezvous1.getId());
        assertThat(tabRendezvous1).isEqualTo(tabRendezvous2);
        tabRendezvous2.setId(2L);
        assertThat(tabRendezvous1).isNotEqualTo(tabRendezvous2);
        tabRendezvous1.setId(null);
        assertThat(tabRendezvous1).isNotEqualTo(tabRendezvous2);
    }
}
