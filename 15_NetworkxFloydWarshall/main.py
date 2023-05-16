import networkx as nx

# Creamos un grafo ponderado con algunos nodos y aristas
G = nx.Graph()
G.add_weighted_edges_from([(1, 2, 3), (2, 3, 1), (3, 4, 5), (4, 1, 2), (1, 3, 4)])

# Utilizamos el algoritmo de Floyd-Warshall para encontrar la ruta más corta entre todos los pares de nodos
distances = nx.floyd_warshall(G)

# Imprimimos las distancias de la ruta más corta entre todos los pares de nodos
for i in distances:
    for j in distances[i]:
        print(f"La distancia de {i} a {j} es {distances[i][j]}")