/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uet.oop.bomberman.gui;

import java.awt.CardLayout;

/**
 *
 * @author HT3rico
 */
public class Container {
        private static final String TAG_MENU = "tag_menu";
	private static final String TAG_PLAYGAME = "tag_playgame";
	private static final String TAG_OPTION = "tag_option";
	private static final String TAG_HIGHTSCORE = "tag_hightscore";
	private CardLayout _cardLayout;
	private Menu _menu;
	private Frame _frame;
//	private Option mOption;
//	private HightScorePanel mHightScorePanel;
        public Container(Menu menu){
            this._menu = menu;
            
        }
}
