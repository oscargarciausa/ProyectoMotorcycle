package com.example.demo.servicio;

import com.example.demo.modelo.Motorbike;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.repositorio.MotorbikeRepository;

import java.util.List;
import java.util.Optional;

@Service
public class MotorbikeService {
    @Autowired
    private MotorbikeRepository motorbikeRepository;

    public List<Motorbike> getAll(){
        return motorbikeRepository.getAll();
    }
    public Optional<Motorbike> getMotorbike(int id){
        return motorbikeRepository.getMotorbike(id);
    }
    public Motorbike save (Motorbike motorbike){
        if(motorbike.getId()==null){
            return motorbikeRepository.save(motorbike);
        }else{
            Optional<Motorbike> motorbikeEncontrado = getMotorbike(motorbike.getId());
            if(motorbikeEncontrado.isEmpty()){
                return motorbikeRepository.save(motorbike);
            }else{
                return motorbike;
            }
        }
    }
    public Motorbike update (Motorbike motorbike){
        if(motorbike.getId() !=null){
            Optional<Motorbike> motorbikeEncontrado = getMotorbike(motorbike.getId());
            if (!motorbikeEncontrado.isEmpty()){
                if(motorbike.getName()!=null){
                    motorbikeEncontrado.get().setName(motorbike.getName());
                }
                if(motorbike.getBrand()!=null){
                    motorbikeEncontrado.get().setBrand(motorbike.getBrand());
                }
                if(motorbike.getYear()!=null){
                    motorbikeEncontrado.get().setYear(motorbike.getYear());
                }
                if(motorbike.getDescription()!=null){
                    motorbikeEncontrado.get().setDescription(motorbike.getDescription());
                }
                if(motorbike.getCategory()!=null){
                    motorbikeEncontrado.get().setCategory(motorbike.getCategory());
                }
                return motorbikeRepository.save(motorbikeEncontrado.get());
            }

        }
        return motorbike;
    }
    public boolean delete(int id){
        Boolean respuesta = getMotorbike(id).map(elemento ->{
            motorbikeRepository.delete(elemento);
            return true;
        }).orElse(false);
        return respuesta;
    }
}
