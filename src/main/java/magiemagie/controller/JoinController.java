/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package magiemagie.controller;

import javax.servlet.http.HttpSession;
import magiemagie.entity.Player;
import magiemagie.service.AvatarServiceCrud;
import magiemagie.service.PlayerServiceCrud;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author admin
 */
@Controller
public class JoinController {
    @Autowired
    private AvatarServiceCrud service;
    
    @Autowired 
    private PlayerServiceCrud playerService;
    
    
    @RequestMapping(value = "/wait", method = RequestMethod.GET)
    public String waitGET(Model model, HttpSession session){
        
        model.addAttribute("players", playerService.findAllByGameId((long) session.getAttribute("currentGameId")));
        
        return "wait.jsp";
    }
    
    @RequestMapping(value = "/join", method = RequestMethod.GET)
    public String joinGET(Model model) {
        model.addAttribute("avatars", service.findAll() );
        
        return "join.jsp";
    }
    
    
   @RequestMapping(value = "/join", method = RequestMethod.POST)
   public String joinPOST(@ModelAttribute ("avatars")Player player) {
        
       // Persiste player en base
       playerService.save(player);
       
       // Associe player à une partie ( en base )
       
       
        return "redirect:/wait";
    }
    
    
    
}
