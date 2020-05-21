package com.brq.jokenpo.repository;

import com.brq.jokenpo.domain.Jogador;
import com.brq.jokenpo.domain.Movimento;
import com.brq.jokenpo.enums.EnumMovimento;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class MovimentosRepositoryTest {

    @Autowired
    MovimentosRepository repository;


    @Test
    public  void testeSave(){

        Movimento movimento = new Movimento();
        movimento.setEnumMovimento(EnumMovimento.PEDRA);

        Movimento response = repository.save(movimento);

        Assert.assertNotNull(response);

    }

    @Test
    public void testFindById(){

        Movimento movimento = new Movimento();
        movimento.setEnumMovimento(EnumMovimento.PEDRA);
        Long id = 1L;

        repository.save(movimento);

        Assert.assertEquals(EnumMovimento.PEDRA, repository.findById(id).get().getEnumMovimento());
    }

    @Test
    public void testDelete(){
        Movimento movimento = new Movimento();
        movimento.setEnumMovimento(EnumMovimento.PEDRA);

        repository.save(movimento);
        repository.deleteById(1L);

        Assert.assertFalse(repository.findById(1L).isPresent());
    }

}
