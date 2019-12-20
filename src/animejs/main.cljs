(ns animejs.main
  (:require [reagent.core :as r]
            ["animejs" :as anime]))

(def svg-image
  [:svg {:view-box "0 0 215 110"}
   [:polygon {:class "polymorph"
              :points "215,110 0,110 0,0 49.3,0 215,0"}]])

(defn do-it
  []
  (let [morphing (anime (clj->js {:targets ".polymorph"
                                  :points [{:value "215, 110 0, 110 0, 0 47.7, 0 67, 76"}
                                           {:value "215, 110 0, 110 0, 0 0, 0 67, 76" }]
                                  :easing "easeOutQuad"
                                  :duration 1200
                                  :loop false}))]
    (anime (clj->js {:targets "#blip"
                     :opacity 1
                     :duration 500
                     :translateY 150}))))

(defn undo-it
  []
  (let [morphing (anime (clj->js {:targets ".polymorph"
                                  :points [{:value "215, 110 0, 110 0, 0 47.7, 0 67, 76"}
                                           {:value "215,110 0,110 0,0 49.3,0 215,0"}]
                                  :easing "easeOutQuad"
                                  :duration 1200
                                  :loop false}))]
    (anime (clj->js {:targets "#blip"
                     :opacity 0
                     :duration 500
                     :translateY -800}))))

(defn app []
  [:div
   [:button {:on-click do-it} "Click Me"]
   svg-image
   [:div#id
    [:h1 "Whoosh! That morphed"]
    [:p "Istn't it cool?"]
    [:p "Code from tutorial at "
     [:a {:href "https://codeburst.io/create-a-revealing-animation-with-anime-js-25a49bc98cd7"}
      "Create a revealing animation with Anime.JS"]
     " by Vaibhav Khulbe"]
    [:button {:on-click undo-it} "Go Back"]]])

(defn ^:dev/after-load start []
  (r/render [app]
            (.getElementById js/document "app")))

(defn ^:export main []
  (start))
